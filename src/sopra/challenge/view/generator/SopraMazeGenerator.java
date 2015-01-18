
package sopra.challenge.view.generator;

import java.util.Random;

import sopra.challenge.control.MatriceConverteur;
import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.ZoneDepart;
import sopra.challenge.view.SopraMazeMazeGame;
import sopra.challenge.view.light.WSDAYTIME;

import com.ardor3d.math.MathUtils;
import com.ardorcraft.data.Pos;
import com.ardorcraft.generators.DataGenerator;
import com.ardorcraft.world.BlockWorld;
import com.ardorcraft.world.WorldModifier;

public class SopraMazeGenerator implements DataGenerator {
    private final int nrLayers;
    private Labyrinthe labyrinthe;
    
    

    public SopraMazeGenerator() {
    	this.labyrinthe = Labyrinthe.getInstance();
        this.nrLayers = 1;
    }

    @Override
    public void generateChunk(final int xStart, final int zStart, final int xEnd, final int zEnd, int spacing,
            final int height, final WorldModifier blockScene) {
    	
		//System.out.println("generate chunk:"+xStart+";"+zStart+";"+xEnd+";"+zEnd+";"+spacing+";"+height);
    	
        for (int x = xStart; x < xEnd; x++) {
            for (int z = zStart; z < zEnd; z++) {
            	
            	Bloc b = Labyrinthe.getInstance().getBloc(x, z);
            	if(b != null && b.isZoneDepart() && ((ZoneDepart)b).getMilieu()) {
            		addTree(blockScene, 
            				new Pos(x,2,z)
            				, 10, new Random(xStart * 10000 + zStart));
            	}
            	else {
            		generateColumn(x, z, height, blockScene);
            	}
            	
                
            }
        }
    	
    }

    private void generateColumn(final int x, final int z, final int height, final WorldModifier blockScene) {
    	int startHeight = 0;
    	int localHeight = Math.max(0, this.getLayerHeight(1, x, 1, z, blockScene));
    	//System.out.println("generateColumn: "+x+";"+z+";"+height);
    	
    	for(int y = startHeight; y < localHeight; y++) {
   
    		blockScene.setBlock(x,y,z,getLayerType(x,y, z, blockScene));
    		startHeight++;
    	}
    	
    	for (int y = startHeight; y < height; y++) {
            blockScene.setBlock(x, y, z, 0);
        }
    }
    
    private boolean isCamp(int x, int z) {
    	
    	Bloc b = Labyrinthe.getInstance().getBloc(x, z);
    	if(b != null) {
    		return b.isZoneDepart() || b.isZoneArrivee();
    	}
    	return false;
    	//code à deplacer dans le labyrinthe et à traiter bien sur
    }

    public int getLayerType(int x, int y, int z, WorldModifier blockScene){
    	if(y <= 1 ) {
    		if(isCamp(x, z))
    			return TypeLayer.getType(TypeLayer.GRASS);
    		else
    			return TypeLayer.getType(TypeLayer.STONE);
    	}
    	else 
    		return TypeLayer.getType(MatriceConverteur.getBloc(x, z, true));
    		//return TypeLayer.getType(MatriceConverteur.getBloc(x, z, false));
    }


    public int getLayerHeight(int layer, int x, int y, int z, WorldModifier blockScene){
    	return MatriceConverteur.getHauteur(x, z);
    }
    
    private void addTree(final WorldModifier blockScene, final Pos pos, final int treeHeight, final Random rand) {
        for (int y = 0; y < treeHeight; y++) {
            blockScene.setBlock(pos.x, pos.y + y, pos.z, 17);
        }

        for (int x = 0; x < treeHeight; x++) {
            for (int z = 0; z < treeHeight; z++) {
                for (int y = 0; y < treeHeight; y++) {
                    final int xx = x - (treeHeight - 1) / 2;
                    final int yy = y - (treeHeight - 1) / 2;
                    final int zz = z - (treeHeight - 1) / 2;
                    if (xx == 0 && zz == 0 && yy <= 0) {
                        continue;
                    }
                    final double test = MathUtils.sqrt((double) xx * xx + yy * yy + zz * zz);
                    if (test < (treeHeight - 1.0) / 2.0) {
                        if (rand.nextDouble() < 0.8) {
                            blockScene.setBlock(pos.x + xx, pos.y + yy + treeHeight - 1, pos.z + zz, 18);
                        }
                    }
                }
            }
        }
    }

	public void regenerate(SopraMazeMazeGame sopraMazeMazeGame,
			Labyrinthe lab) {
		for(Bloc b: lab.blocs) {
			int startHeight = 0;
			int x = b.getPositionBloc().coordX;
			int z = b.getPositionBloc().coordY;
	    	int localHeight = Math.max(0, this.getLayerHeight(1, x, 1, z, null));
	    	
	    	for(int y = startHeight; y < localHeight; y++) {
	    		   
	    		sopraMazeMazeGame.getBlockWorld().setBlock(x, y, z, getLayerType(x,y, z, null));
	    	}
	    	
			
		}
	}
}
