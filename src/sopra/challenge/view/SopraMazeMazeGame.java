/**
 * Copyright (c) 2008-2010 Ardor Labs, Inc.
 *
 * This file is part of Ardor3D.
 *
 * Ardor3D is free software: you can redistribute it and/or modify it 
 * under the terms of its license which may be found in the accompanying
 * LICENSE file or at <http://www.ardor3d.com/LICENSE>.
 */

package sopra.challenge.view;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Callable;

import javax.swing.WindowConstants;

import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.fabrique.FabriqueLabyrinthe;
import sopra.challenge.view.generator.SopraMazeGenerator;
import sopra.challenge.view.impor.*;
//import sopra.challenge.view.impor.LocalServerDataHandler.LocalBlockModifier;
import sopra.challenge.view.light.LightController;
import sopra.challenge.view.light.LightListener;
import sopra.challenge.view.light.LightListenerComposite;
import sopra.challenge.view.light.LightListenerGame;
import sopra.challenge.view.light.LightManager;
import sopra.challenge.view.light.SimpleLightManager;
import sopra.challenge.view.light.TextLightListener;

import com.ardor3d.framework.Canvas;
import com.ardor3d.input.GrabbedState;
import com.ardor3d.input.Key;
import com.ardor3d.input.MouseManager;
import com.ardor3d.input.PhysicalLayer;
import com.ardor3d.input.logical.InputTrigger;
import com.ardor3d.input.logical.KeyHeldCondition;
import com.ardor3d.input.logical.KeyPressedCondition;
import com.ardor3d.input.logical.LogicalLayer;
import com.ardor3d.input.logical.TriggerAction;
import com.ardor3d.input.logical.TwoInputStates;
import com.ardor3d.math.ColorRGBA;
import com.ardor3d.math.MathUtils;
import com.ardor3d.math.Vector3;
import com.ardor3d.math.type.ReadOnlyColorRGBA;
import com.ardor3d.renderer.Camera;
import com.ardor3d.renderer.ContextManager;
import com.ardor3d.renderer.Renderer;
import com.ardor3d.renderer.queue.RenderBucketType;
import com.ardor3d.renderer.state.BlendState;
import com.ardor3d.renderer.state.FogState;
import com.ardor3d.renderer.state.WireframeState;
import com.ardor3d.scenegraph.Node;
import com.ardor3d.scenegraph.hint.LightCombineMode;
import com.ardor3d.scenegraph.hint.NormalsMode;
import com.ardor3d.ui.text.BasicText;
import com.ardor3d.util.GameTaskQueue;
import com.ardor3d.util.GameTaskQueueManager;
import com.ardor3d.util.ReadOnlyTimer;
import com.ardor3d.util.resource.ResourceLocatorTool;
import com.ardor3d.util.resource.SimpleResourceLocator;
import com.ardorcraft.collision.IntersectionResult;
import com.ardorcraft.data.Pos;
import com.ardorcraft.generators.DataGenerator;
import com.ardorcraft.objects.QuadBox;
import com.ardorcraft.objects.SkyDome;
import com.ardorcraft.util.geometryproducers.BoxProducer;
import com.ardorcraft.world.BlockSide;
import com.ardorcraft.world.BlockWorld;
import com.ardorcraft.world.IServerConnection;
import com.ardorcraft.world.WorldModifier;
import com.ardorcraft.world.WorldSettings;

/**
 * A bigger example that will grow over time...
 */
public class SopraMazeMazeGame implements ArdorCraftGame {
	public static final String TEXT_INFO_ID = "TextJour";
	private BlockWorld blockWorld;
	private final int tileSize = 16;
	private final int height = 150;
	private double farPlane = 10000.0;

	private final IntersectionResult intersectionResult = new IntersectionResult();

	private final FogState fogState = new FogState();
	private final ColorRGBA fogColor = new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f);
	private final ColorRGBA topColor = new ColorRGBA(0.5f, 0.6f, 1.0f, 1.0f);

	private CanvasRelayer canvas;
	private Node root;
	private Camera camera;
	private PlayerWithPhysics player;

	private Node worldNode;
	private Node textNode;
	private Node textNode3;
	private Node textEndNode;
	private SkyDome skyDome;
	private QuadBox selectionBox;

	private int blockType = 1;
	private float globalLight = 1f;
	private boolean isInWater = false;
	private final int[] blockTypeLookup = new int[] {
			1, 47, 4, 5, 20, 95, 12, 45, 48, 50
	};

	private Labyrinthe labyrinthe;
	private LightController lightController;
	private Node textNode2;
	
	private End endCode;

	public SopraMazeMazeGame(Labyrinthe l) {
		this.labyrinthe = l;
		endCode = End.NO_END;
	}

	@Override
	public void update(final ReadOnlyTimer timer) {
		
		player.update(blockWorld, timer);

		blockWorld.tracePicking(player.getPosition(), player.getDirection(), 50, intersectionResult);
		if (intersectionResult.hit) {
			final Pos hitPos = intersectionResult.pos;
			checkFin(hitPos);
			updateTextFin();
			selectionBox.setTranslation(hitPos.x + 0.5, hitPos.y + 0.5, hitPos.z + 0.5);
		}

		camera.setLocation(player.getPosition());
		camera.setDirection(player.getDirection());
		camera.setUp(player.getUp());
		camera.setLeft(player.getLeft());

		skyDome.setTranslation(player.getPosition());

		updateFog(player.getPosition());

		// The infinite world update
		blockWorld.updatePlayer(player.getPosition(), player.getDirection());
		blockWorld.update(timer);
		lightController.timeRun();
	}

	private void checkFin(Pos pos) {
		System.out.println(pos);
		Bloc bloc = labyrinthe.getBloc(pos.x, pos.z);
		if(bloc != null && bloc.isZoneArrivee()) {
			endCode = End.EXIT;
		}
	}
	
	private void updateTextFin() {
		textEndNode.detachAllChildren();
		if(endCode != End.NO_END) {
			createText(endCode.text(), canvas.getCanvasRenderer().getCamera().getWidth() / 2 - 50, canvas.getCanvasRenderer()
                .getCamera().getHeight() / 2 - 5, textEndNode);
		}
		
	}

	@Override
	public void render(final Renderer renderer) {
		// root.draw(renderer);

		// Taking over the drawing to draw in specific order without performance
		// hogging renderqueue sorting...
		skyDome.draw(renderer);
		worldNode.draw(renderer);
		if (intersectionResult.hit) {
			selectionBox.draw(renderer);
		}
		textNode.draw(renderer);
		textNode2.draw(renderer);
		textNode3.draw(renderer);
		textEndNode.draw(renderer);
	}

	@Override
	public void init(final Node root, final CanvasRelayer canvas, final LogicalLayer logicalLayer,
			final PhysicalLayer physicalLayer, final MouseManager mouseManager) {
		this.root = root;
		this.canvas = canvas;

		try {
			final SimpleResourceLocator srl = new SimpleResourceLocator(ResourceLocatorTool.getClassPathResource(
					SopraMazeMazeGame.class, "com/ardorcraft/resources"));
			ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_TEXTURE, srl);
			ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_MODEL, srl);
		} catch (final URISyntaxException ex) {
			ex.printStackTrace();
		}
		
		canvas.setTitle("Sopra Maze - 24h du code 2015");

		final SelectDialog dialog = new SelectDialog();
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		
		DataGenerator dataGenerator = null;
		try {
			dataGenerator = (DataGenerator) dialog.getSelectedGenerator().newInstance();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final String texture = dialog.getSelectedTexture();
		final int textureTileSize = dialog.getSelectedTextureSize();
		final boolean doOverwriteMap = dialog.getIsOverwriteMap();
		final int gridSize = dialog.getViewDistance();

		farPlane = (gridSize - 1) / 2 * tileSize;

		camera = canvas.getCanvasRenderer().getCamera();
		camera.setFrustumPerspective(75.0, (float) camera.getWidth() / (float) camera.getHeight(), 0.1, farPlane);

		setupFog();

		// Create player object
		player = new PlayerWithPhysics(logicalLayer);
		
		Bloc depart = Labyrinthe.getInstance().getDepart();
		if(depart != null) {
			player.getPosition().set(depart.getPositionBloc().coordX+2, 2, depart.getPositionBloc().coordY+2);
		}
		else {
			player.getPosition().set(0, 20, 0);
		}
		
		
		player.setWalking(true);

		registerTriggers(logicalLayer, mouseManager);

		// Map file to use
		final File worldFileSource = new File(dialog.getSelectedGenerator().getSimpleName() + "_Map.acr");
		// Uncomment this if you want to start your mapfile from scratch each run...
		if (doOverwriteMap && worldFileSource.exists()) {
			worldFileSource.delete();
		}

		// Create main blockworld handler
		final WorldSettings settings = new WorldSettings();

		// Here you can load any terrain texture you wish (should contain 16x16 tiles).
		// Just make sure you set the correct tilesize, that is, the subtexture size in pixels.
		settings.setTerrainTexture(ResourceLocatorTool.locateResource(ResourceLocatorTool.TYPE_TEXTURE, texture));
		settings.setTerrainTextureTileSize(textureTileSize);

		settings.setWaterTexture(ResourceLocatorTool.locateResource(ResourceLocatorTool.TYPE_TEXTURE, "water.png"));
		settings.setTileSize(tileSize);
		settings.setTileHeight(height);
		settings.setGridSize(gridSize);

		final IServerConnection serverConnection = new LocalServerConnection(new LocalServerDataHandler(tileSize,
				height, gridSize, dataGenerator, null));
		settings.setServerConnection(serverConnection);

		blockWorld = new BlockWorld(settings);

		worldNode = blockWorld.getWorldNode();
		root.attachChild(worldNode);

		skyDome = new SkyDome("Dome", 8, 8, 10);
		root.attachChild(skyDome);

		textNode = new Node("text");
		root.attachChild(textNode);
		createText("JOUR", 10, 10,textNode);
		textNode2 = new Node("compteur");
		root.attachChild(textNode2);
		
		textNode3 = new Node("textConsigne");
        root.attachChild(textNode3);
        createText("+", canvas.getCanvasRenderer().getCamera().getWidth() / 2 - 5, canvas.getCanvasRenderer()
                .getCamera().getHeight() / 2 - 10,textNode3);
        createText("Trouver la sortie du labyrinthe pendant  la journ�e.", 10, 65,textNode3);
        createText("Attention aux monstres la nuit !!!", 10, 50,textNode3);
        
        textEndNode = new Node("textEnd");
        root.attachChild(textEndNode);
        
		// Create box to show selected box
		selectionBox = new QuadBox("SelectionBox", new Vector3(), 0.501, 0.501, 0.501);
		selectionBox.getSceneHints().setNormalsMode(NormalsMode.Off);
		selectionBox.setDefaultColor(new ColorRGBA(0.1f, 0.1f, 0.1f, 0.4f));
		selectionBox.getSceneHints().setRenderBucketType(RenderBucketType.Skip);
		final BlendState bs = new BlendState();
		bs.setBlendEnabled(true);
		selectionBox.setRenderState(bs);
		final WireframeState ws = new WireframeState();
		ws.setLineWidth(2);
		selectionBox.setRenderState(ws);
		selectionBox.getSceneHints().setLightCombineMode(LightCombineMode.Off);
		root.attachChild(selectionBox);

		updateLighting();

		// Ajout d'un light manager sur le monde
		LightManager lightManager = new SimpleLightManager(blockWorld);
		// Création du light contrôleur
		LightListenerGame listener1 = LightListenerGame.getInstance();
		LightListener listener2 = new TextLightListener(textNode);
		LightListenerComposite listener = new LightListenerComposite();
		listener.addListener(listener1);
		listener.addListener(listener2);
		listener1.setGame(this);
		lightController = new LightController(lightManager, listener, textNode2);
		
		createText(String.format("%.0f%%", lightManager.tauxAvancementDayOrNight()), 20, 10,textNode2);

		blockWorld.startThreads();
		
	}

	private void createText(final String text, final int x, final int y,Node textNodeAModif) {
		final BasicText info = BasicText.createDefaultTextLabel(TEXT_INFO_ID, text, 16);
		info.getSceneHints().setRenderBucketType(RenderBucketType.Ortho);
		info.setTranslation(new Vector3(x, y, 0));
		textNodeAModif.attachChild(info);
	}
	


	private void updateLighting() {
		final float light = globalLight * 0.9f + 0.1f;
		final ReadOnlyColorRGBA newColor = new ColorRGBA(fogColor).multiplyLocal(light);
		fogState.setColor(newColor);
		skyDome.getMidColor().set(newColor);
		skyDome.getTopColor().set(topColor).multiplyLocal(light);
		skyDome.updateColors();

		GameTaskQueueManager.getManager(ContextManager.getCurrentContext()).getQueue(GameTaskQueue.RENDER)
		.enqueue(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				canvas.getCanvasRenderer().getRenderer().setBackgroundColor(newColor);
				return true;
			}
		});
	}

	private void setupFog() {
		fogState.setDensity(1.0f);
		fogState.setEnabled(true);
		fogState.setEnd((float) farPlane);
		fogState.setStart((float) farPlane / 3.0f);
		fogState.setDensityFunction(FogState.DensityFunction.Linear);
		fogState.setQuality(FogState.Quality.PerPixel);
		root.setRenderState(fogState);
	}

	private void updateFog(final Vector3 position) {
		final int block = blockWorld.getBlock((int) position.getX(), (int) (position.getY() + 0.15),
				(int) position.getZ());
		if (block == BlockWorld.WATER && !isInWater) {
			isInWater = true;
			fogColor.set(0.1f, 0.13f, 0.25f, 1.0f);
			topColor.set(0.15f, 0.2f, 0.35f, 1.0f);
			fogState.setStart(0);
			fogState.setEnd((float) farPlane / 8);
			updateLighting();
		} else if (block == 0 && isInWater) {
			isInWater = false;
			fogColor.set(1.0f, 1.0f, 1.0f, 1.0f);
			topColor.set(0.5f, 0.6f, 1.0f, 1.0f);
			fogState.setEnd((float) farPlane);
			fogState.setStart((float) farPlane / 3.0f);
			updateLighting();
		}
	}

	private void registerTriggers(final LogicalLayer logicalLayer, final MouseManager mouseManager) {

		logicalLayer.registerTrigger(new InputTrigger(new KeyPressedCondition(Key.F), new TriggerAction() {
			@Override
			public void perform(final Canvas source, final TwoInputStates inputState, final double tpf) {
				player.setWalking(!player.isWalking());
			}
		}));

		logicalLayer.registerTrigger(new InputTrigger(new KeyHeldCondition(Key.SPACE), new TriggerAction() {
			@Override
			public void perform(final Canvas source, final TwoInputStates inputState, final double tpf) {
				player.jump();
			}
		}));


		if (mouseManager.isSetGrabbedSupported()) {
			mouseManager.setGrabbed(GrabbedState.GRABBED);
		}
	}

	@Override
	public void destroy() {
		blockWorld.stopThreads();
	}

	@Override
	public void resize(final int newWidth, final int newHeight) {}

	private void addBlock() {
		if (intersectionResult.hit) {
			final Pos addPos = intersectionResult.oldPos;
			if (!player.isPlayerSpace(addPos)) {
				final BlockSide orientation = getOrientation(blockType);
				blockWorld.setBlock(addPos.x, addPos.y, addPos.z, blockType, orientation);
			}
		}
	}

	private void removeBlock() {
		if (intersectionResult.hit) {
			final Pos deletePos = intersectionResult.pos;
			blockWorld.setBlock(deletePos.x, deletePos.y, deletePos.z, 0);

			final int aboveBlock = blockWorld.getBlock(deletePos.x, deletePos.y + 1, deletePos.z);
			if (aboveBlock > 99 && aboveBlock < 108) {
				blockWorld.setBlock(deletePos.x, deletePos.y + 1, deletePos.z, 0);
			}
		}
	}

	private BlockSide getOrientation(final int blockId) {
		BlockSide orientation = BlockSide.Front;

		if (blockWorld.getBlockUtil().getGeometryProducer(blockId) instanceof BoxProducer) {
			final Vector3 dir = player.getDirection();
			if (Math.abs(dir.getX()) > Math.abs(dir.getZ())) {
				if (dir.getX() < 0) {
					orientation = BlockSide.Right;
				} else {
					orientation = BlockSide.Left;
				}
			} else {
				if (dir.getZ() < 0) {
					orientation = BlockSide.Front;
				} else {
					orientation = BlockSide.Back;
				}
			}
		} else {
			final int xDir = intersectionResult.oldPos.x - intersectionResult.pos.x;
			final int yDir = intersectionResult.oldPos.y - intersectionResult.pos.y;
			final int zDir = intersectionResult.oldPos.z - intersectionResult.pos.z;
			if (xDir != 0) {
				if (xDir > 0) {
					orientation = BlockSide.Right;
				} else {
					orientation = BlockSide.Left;
				}
			} else if (yDir != 0) {
				if (yDir > 0) {
					orientation = BlockSide.Bottom;
				} else {
					orientation = BlockSide.Top;
				}
			} else if (zDir != 0) {
				if (zDir > 0) {
					orientation = BlockSide.Front;
				} else {
					orientation = BlockSide.Back;
				}
			}
		}

		return orientation;
	}

	public BlockWorld getBlockWorld() {
		return blockWorld;
	}
	
	public void reload() {
		FabriqueLabyrinthe.clean();
		FabriqueLabyrinthe.labAlea();
		new SopraMazeGenerator().regenerate(this, Labyrinthe.getInstance());
	}
	
	public End getEndCode() {
		return endCode;
	}
}
