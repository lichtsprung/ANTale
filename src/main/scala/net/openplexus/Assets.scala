package net.openplexus

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.Gdx

/**
 * All assets of the game.
 */
object Assets {
  private val sprites: TextureAtlas = new TextureAtlas(Gdx.files.internal("pack"))
  val GROUND_GRASS1 = sprites.findRegion("grass")
}
