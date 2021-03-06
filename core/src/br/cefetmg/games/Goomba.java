/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Aluno
 */
public class Goomba {
    
    Texture spriteSheet;
    TextureRegion[][] quadrosDaAnimacao;
    Animation andarParaFrente;
    Animation andarParaDireita;
    Animation andarParaEsquerda;
    Animation andarParaTras;
    Animation animacaoAtual;
    float tempoDaAnimacao;
    
    private Sprite jogador;
    public int x = 30;
    public int y = 10;
    
    private int parado = 1;
    
    public Goomba(Texture spriteSheet){
        //this.jogador=jogador;
        this.spriteSheet = spriteSheet;
        
        spriteSheet = new Texture("goomba-spritesheet.png");
        quadrosDaAnimacao = TextureRegion.split(spriteSheet, 21, 24);
        
        andarParaFrente = new Animation(0.1f,
          quadrosDaAnimacao[0][0], // 1ª linha, 1ª coluna
          quadrosDaAnimacao[0][1], // idem, 2ª coluna
          quadrosDaAnimacao[0][2],
          quadrosDaAnimacao[0][3],
          quadrosDaAnimacao[0][4]);
        
        andarParaDireita = new Animation(0.1f,
          quadrosDaAnimacao[1][0], // 1ª linha, 1ª coluna
          quadrosDaAnimacao[1][1], // idem, 2ª coluna
          quadrosDaAnimacao[1][2],
          quadrosDaAnimacao[1][3],
          quadrosDaAnimacao[1][4]);
        
        
        andarParaEsquerda = new Animation(0.1f,
          quadrosDaAnimacao[3][0], // 1ª linha, 1ª coluna
          quadrosDaAnimacao[3][1], // idem, 2ª coluna
          quadrosDaAnimacao[3][2],
          quadrosDaAnimacao[3][3],
          quadrosDaAnimacao[3][4]);
        
        
        andarParaTras = new Animation(0.1f,
          quadrosDaAnimacao[2][0], // 1ª linha, 1ª coluna
          quadrosDaAnimacao[2][1], // idem, 2ª coluna
          quadrosDaAnimacao[2][2],
          quadrosDaAnimacao[2][3],
          quadrosDaAnimacao[2][4]);
        
        animacaoAtual=andarParaFrente;
        tempoDaAnimacao = 0;
        //jogador.setPosition(x, y);
    }
    
    public void update() {//int x, int y){
        
        tempoDaAnimacao += Gdx.graphics.getDeltaTime();
                
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(x<Gdx.graphics.getWidth()){
                animacaoAtual = andarParaDireita;  
                x=x+2;
                parado=0;
            }
                  
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(x>0){
                animacaoAtual = andarParaEsquerda;
                x=x-2;
                parado=0;
            }
                
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(y<Gdx.graphics.getHeight()){
                animacaoAtual = andarParaTras;
                y=y+2;
                parado=0;
            }
                
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(y>0){
                animacaoAtual = andarParaFrente;
                y=y-2;
                parado=0;
            }
                
        }
        else{
            parado=1;
        }
        //jogador.setPosition(x, y);
    }
    
    public void render(SpriteBatch batch) {
            //jogador.draw(batch);
            tempoDaAnimacao += Gdx.graphics.getDeltaTime();
            TextureRegion quadroCorrente = (TextureRegion)
            animacaoAtual.getKeyFrame(tempoDaAnimacao);
            batch.draw(quadroCorrente, x, y);
            if(parado==0)
                animacaoAtual.setPlayMode(PlayMode.LOOP_PINGPONG);
            else
                animacaoAtual.setPlayMode(PlayMode.NORMAL);

    }
}
