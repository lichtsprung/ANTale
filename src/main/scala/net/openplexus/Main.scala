package net.openplexus

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

/**
 * Starts the game.
 */
object Main extends App{
  val config = new LwjglApplicationConfiguration()
  config.width = 800
  config.height = 600
  config.title  = "ANTale"
  new LwjglApplication(new Game(config.width, config.height), config)

}
