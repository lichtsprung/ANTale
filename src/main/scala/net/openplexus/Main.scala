package net.openplexus

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

/**
 * Starts the game.
 */
object Main extends App {

  /*val settings = new Settings()
  settings.maxHeight = 4096
  settings.maxWidth = 4096
  TexturePacker2.process(settings, "textures/unpacked", "textures/packed", "sprites")*/

  val config = new LwjglApplicationConfiguration()
  config.width = 800
  config.height = 600
  config.title = "ANTale"
  new LwjglApplication(Game(config.width, config.height, 32), config)

}
