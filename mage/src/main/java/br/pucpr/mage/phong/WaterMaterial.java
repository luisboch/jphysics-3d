package br.pucpr.mage.phong;

import br.pucpr.mage.FrameBuffer;
import br.pucpr.mage.SimpleMaterial;

public class WaterMaterial extends SimpleMaterial {
    public WaterMaterial() {
        super("phong/water");
    }
    
    @Override
    public void apply() {
        super.apply();
    }

	public void setRefraction(FrameBuffer refractionFB) {
		setTexture("uRefraction", refractionFB.getTexture());
	}

	public void setReflection(FrameBuffer reflectionFB) {
		setTexture("uReflection", reflectionFB.getTexture());		
	}
}
