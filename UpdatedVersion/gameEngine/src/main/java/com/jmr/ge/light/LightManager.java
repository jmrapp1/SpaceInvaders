package com.jmr.ge.light;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.jmr.ge.camera.OrthoCamera;
import com.jmr.ge.textures.ResourceManager;

public class LightManager {

	private Array<Light> lights = new Array<Light>();
	
	private float ambientIntensity = .7f;
	private Vector3 ambientColor = new Vector3(0.3f, 0.3f, 0.7f);

	private FrameBuffer fbo;
	private ShaderProgram defaultShader;
	private ShaderProgram finalShader;
	
	private final String vertexShader = Gdx.files.internal("lighting/vertexShader.glsl").readString();
	private final String defaultPixelShader = Gdx.files.internal("lighting/defaultPixelShader.glsl").readString();
	private final String finalPixelShader =  Gdx.files.internal("lighting/pixelShader.glsl").readString();
	
	public LightManager() {
		setupLighting();
	}
	
	public LightManager(float ambientIntensity, Vector3 ambientColor) {
		this.ambientIntensity = ambientIntensity;
		this.ambientColor = ambientColor;
		setupLighting();
	}
	
	private void setupLighting() {
		ShaderProgram.pedantic = false;
		defaultShader = new ShaderProgram(vertexShader, defaultPixelShader);
		finalShader = new ShaderProgram(vertexShader, finalPixelShader);
		
		finalShader.begin();
		finalShader.setUniformi("u_lightmap", 1);
		finalShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y,
				ambientColor.z, ambientIntensity);
		finalShader.end();

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}
	
	public void render(SpriteBatch sb, OrthoCamera camera) {
		fbo.begin();
		sb.setProjectionMatrix(camera.combined);
		sb.setShader(defaultShader);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		for (Light light : lights)
			light.render(sb);
		sb.end();
		fbo.end();
		
		//draw the actual scene
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(camera.combined);
		sb.setShader(finalShader);
		sb.begin();
		fbo.getColorBufferTexture().bind(1); //this is important! bind the FBO to the 2nd texture unit
		ResourceManager.getInstance().getTexture("light").bind(0); //we force the binding of a texture on first texture unit to avoid artefacts
		
		/* THIS NEEDS TO BE ENDED AFTER GAME IS DRAWN
		sb.end();
		sb.setShader(defaultShader);
		*/
	}
	
	public void addLight(Light light) {
		lights.add(light);
	}
	
	public void setAmbient(float ambientIntensity, Vector3 ambientColor) {
		this.ambientIntensity = ambientIntensity;
		this.ambientColor = ambientColor;
		setupLighting();
	}
	
	public void dispose() {
		finalShader.dispose();
		defaultShader.dispose();
		fbo.dispose();
	}
	
	public void resize(int width, int height) {
		fbo = new FrameBuffer(Format.RGBA8888, width, height, false);

		finalShader.begin();
		finalShader.setUniformf("resolution", width, height);
		finalShader.end();
	}
	
}
