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

import java.net.URISyntaxException;

import com.ardor3d.framework.Canvas;
import com.ardor3d.input.GrabbedState;
import com.ardor3d.input.Key;
import com.ardor3d.input.MouseButton;
import com.ardor3d.input.MouseManager;
import com.ardor3d.input.PhysicalLayer;
import com.ardor3d.input.logical.InputTrigger;
import com.ardor3d.input.logical.KeyHeldCondition;
import com.ardor3d.input.logical.KeyPressedCondition;
import com.ardor3d.input.logical.LogicalLayer;
import com.ardor3d.input.logical.MouseButtonPressedCondition;
import com.ardor3d.input.logical.TriggerAction;
import com.ardor3d.input.logical.TwoInputStates;
import com.ardor3d.math.ColorRGBA;
import com.ardor3d.math.MathUtils;
import com.ardor3d.math.Vector3;
import com.ardor3d.renderer.Camera;
import com.ardor3d.renderer.Renderer;
import com.ardor3d.renderer.queue.RenderBucketType;
import com.ardor3d.scenegraph.Node;
import com.ardor3d.scenegraph.shape.Teapot;
import com.ardor3d.ui.text.BasicText;
import com.ardor3d.util.ReadOnlyTimer;
import com.ardor3d.util.resource.ResourceLocatorTool;
import com.ardor3d.util.resource.SimpleResourceLocator;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.view.generator.SopraMazeGenerator;
import sopra.challenge.view.impor.ArdorCraftGame;
import sopra.challenge.view.impor.CanvasRelayer;
import sopra.challenge.view.impor.PlayerWithPhysics;

import com.ardorcraft.collision.IntersectionResult;
import com.ardorcraft.control.FlyControl;
import com.ardorcraft.data.Pos;

import sopra.challenge.view.impor.LocalServerConnection;
import sopra.challenge.view.impor.LocalServerDataHandler;
import sopra.challenge.view.light.LightController;
import sopra.challenge.view.light.LightListenerGame;
import sopra.challenge.view.light.LightManager;
import sopra.challenge.view.light.SimpleLightManager;

import com.ardorcraft.objects.SkyDome;
import com.ardorcraft.player.PlayerBase;
import com.ardorcraft.voxel.Voxelator;
import com.ardorcraft.world.BlockWorld;
import com.ardorcraft.world.IServerConnection;
import com.ardorcraft.world.WorldModifier;
import com.ardorcraft.world.WorldSettings;
import com.google.common.base.Predicate;


/**
 * A simple example showing the very basics of block world building
 */
public class SopraMazeGame implements ArdorCraftGame {

	private BlockWorld blockWorld;
	private final int tileSize = 16;
	private final int gridSize = 50;
	private final int height = 30;
	private Node root;
	private Camera camera;
	private PlayerWithPhysics player;
	private Labyrinthe labyrinthe;
	private SkyDome skyDome;
	private LightController lightController;
	private final IntersectionResult intersectionResult = new IntersectionResult();
	private Node textNode;

	public SopraMazeGame(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;
	}
	@Override
	public void update(final ReadOnlyTimer timer) {

		player.update(blockWorld, timer);
//		blockWorld.tracePicking(player.getPosition(), player.getDirection(), 50, intersectionResult);
//		if (intersectionResult.hit) {
//			final Pos hitPos = intersectionResult.pos;
//		}

		camera.setLocation(player.getPosition());
		camera.setDirection(player.getDirection());
		camera.setUp(player.getUp());
		camera.setLeft(player.getLeft());

		skyDome.setTranslation(player.getPosition());

		// The infinite world update
		blockWorld.updatePlayer(player.getPosition(), player.getDirection());
		blockWorld.update(timer);
		lightController.timeRun();
	}

	@Override
	public void render(final Renderer renderer) {
		skyDome.draw(renderer);
		root.draw(renderer);
	}

	@Override
	public void init(final Node root, final CanvasRelayer canvas, final LogicalLayer logicalLayer,
			final PhysicalLayer physicalLayer, final MouseManager mouseManager) {
		this.root = root;

		// Make sure the world can find the textures.
		try {
			final SimpleResourceLocator srl = new SimpleResourceLocator(ResourceLocatorTool.getClassPathResource(
					SopraMazeGame.class, "com/ardorcraft/resources"));
			ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_TEXTURE, srl);
		} catch (final URISyntaxException ex) {
			ex.printStackTrace();
		}

		// Some window details
		canvas.setTitle("Sopra Steria 24h du code");
		canvas.getCanvasRenderer().getRenderer().setBackgroundColor(ColorRGBA.WHITE);

		// Setup camera fov and view distance
		camera = canvas.getCanvasRenderer().getCamera();
		camera.setFrustumPerspective(75.0, (float) camera.getWidth() / (float) camera.getHeight(), 0.1, 1000);

		// Create player object
		player = new PlayerWithPhysics(logicalLayer);
		if(Labyrinthe.getInstance().getDepart() != null) {
			player.getPosition().set(Labyrinthe.getInstance().getDepart().getPositionBloc().coordX,   15,  Labyrinthe.getInstance().getDepart().getPositionBloc().coordY);
		}
		else {
			player.getPosition().set(0,15,0);
		}
		player.setWalking(true);

		FlyControl.setupTriggers(player, logicalLayer, Vector3.UNIT_Y, true);

		registerTriggers(logicalLayer, mouseManager);

		// Create block world settings.
		final WorldSettings settings = new WorldSettings();
		settings.setTerrainTexture(ResourceLocatorTool.locateResource(ResourceLocatorTool.TYPE_TEXTURE, "terrainQ.png"));
		settings.setTerrainTextureTileSize(16);
		settings.setWaterTexture(ResourceLocatorTool.locateResource(ResourceLocatorTool.TYPE_TEXTURE, "water.png"));
		settings.setTileSize(tileSize);
		settings.setTileHeight(height);
		settings.setGridSize(gridSize);
		settings.setUseVBO(false);

		//sky
		skyDome = new SkyDome("Dome", 160, 160, 200);
		root.attachChild(skyDome);
		
		// Node de texte
		textNode = new Node("text");
        root.attachChild(textNode);

		// Create a local "fake" server
		final IServerConnection serverConnection = new LocalServerConnection(new LocalServerDataHandler(tileSize,
				height, gridSize, new SopraMazeGenerator(), null));
		settings.setServerConnection(serverConnection);

		// Create the actual world and put its world node under our main scenegraph node.
		blockWorld = new BlockWorld(settings);
		root.attachChild(blockWorld.getWorldNode());
		createText("JOUR", canvas.getCanvasRenderer().getCamera().getWidth() / 2 - 5, canvas.getCanvasRenderer()
                .getCamera().getHeight() / 2 - 10);

//		// Ajout d'un light manager sur le monde
//		LightManager lightManager = new SimpleLightManager(blockWorld);
//		// Création du light contrôleur
//		LightListenerGame listener = LightListenerGame.getInstance();
//		listener.setGame(this);
//		lightController = new LightController(lightManager);

		// Start the processing!
		blockWorld.startThreads();
	}

	@Override
	public void destroy() {
		blockWorld.stopThreads();
	}

	@Override
	public void resize(int newWidth, int newHeight) {
		// TODO Auto-generated method stub

	}
	
	 private void createText(final String text, final int x, final int y) {
        final BasicText info = BasicText.createDefaultTextLabel("Text2", text, 16);
        info.getSceneHints().setRenderBucketType(RenderBucketType.Ortho);
        info.setTranslation(new Vector3(x, y, 0));
        textNode.attachChild(info);
    }

	private void registerTriggers(final LogicalLayer logicalLayer, final MouseManager mouseManager) {


		logicalLayer.registerTrigger(new InputTrigger(new KeyPressedCondition(Key.F), new TriggerAction() {
			@Override
			public void perform(final Canvas source, final TwoInputStates inputState, final double tpf) {
				System.out.println("walk");
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
}
