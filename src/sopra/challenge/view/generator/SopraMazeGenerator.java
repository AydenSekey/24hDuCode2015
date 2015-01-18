
package sopra.challenge.view.generator;

import sopra.challenge.control.MatriceConverteur;
import sopra.challenge.labyrinthe.Bloc;
import sopra.challenge.labyrinthe.Labyrinthe;

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
                generateColumn(x, z, height, blockScene);
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
    	if(y == 1) {
    		if(isCamp(x, z))
    			return TypeLayer.getType(TypeLayer.GRASS);
    		else
    			return TypeLayer.getType(TypeLayer.STONE);
    	}
    	else 
    		return TypeLayer.getType(MatriceConverteur.getBloc(x, z));
    }


    public int getLayerHeight(int layer, int x, int y, int z, WorldModifier blockScene){
    	return MatriceConverteur.getHauteur(x, z);
    }
}
