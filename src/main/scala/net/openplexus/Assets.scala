package net.openplexus

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.Gdx
import collection.mutable
import com.badlogic.gdx.audio.Sound

/**
 * All assets of the game.
 */
object Assets {
  private val sprites: TextureAtlas = new TextureAtlas(Gdx.files.internal("pack"))
  private var sounds = mutable.HashMap[String, Sound]()

  loadSounds()

  def getImage(name: String) = {
    sprites.findRegion(name)
  }

  def getSound(name: String) = {
    sounds.get(name)
  }

  def dispose() {
    sprites.dispose()
  }


  private def loadSounds() {
    sounds += "walk_grass" -> sound("sound/sfx/walk_grass.wav")
  }

  private def sound(file: String) = {
    Gdx.audio.newSound(Gdx.files.internal(file))
  }
}
