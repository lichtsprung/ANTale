package net.openplexus

import com.badlogic.gdx.graphics.g2d.{SpriteBatch, BitmapFont, TextureAtlas}
import com.badlogic.gdx.Gdx
import collection.mutable
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color

/**
 * All assets of the game.
 */
object Assets {
  private val sprites: TextureAtlas = new TextureAtlas(Gdx.files.internal("pack"))
  private val font: BitmapFont = new BitmapFont(Gdx.files.internal("mono.fnt"), false)
  private var chars = mutable.HashMap[String, BitmapFont.TextBounds]()
  private var sounds = mutable.HashMap[String, Sound]()

  loadChars()
  loadSounds()

  def getImage(name: String) = {
    sprites.findRegion(name)
  }

  def drawString(string: String, x: Float, y: Float, batch: SpriteBatch) {
    font.draw(batch, string, x, y)
  }

  def drawString(string: String, x: Float, y: Float, color: Color, batch: SpriteBatch) {
    font.setColor(color)
    font.draw(batch, string, x, y)
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

  private def loadChars() {
    chars += "player" -> font.getBounds("@")
    chars += "empty" -> font.getBounds(".")
  }

  private def sound(file: String) = {
    Gdx.audio.newSound(Gdx.files.internal(file))
  }
}
