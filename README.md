###### Cloned from [Massivecraft/Factions](https://github.com/MassiveCraft/Factions/tree/443e083f23fd7965fa13cc7602b3a507b902cbb7), version 1.4.9

## Changes in this new version: 

* Bug fixes
* Added Towny commands
    * Towny commands are just Faction commands disguised as Towny
    * Use `/town ?` or `/towny ?` to see all the avaliable commands
* Fix to `/f chat` to have a toggle; `/f chat Hello World!` or `/f chat toggle`
* Added `/f here` to show what faction you're standing in

## Factions - Guilding and user-controlled antigrief plugin for Minecraft

This plugin will allow the players on the server to create factions/guilds. The factions can claim territory that will be protected from non-members. Factions can forge alliances and declare themselves enemies with others. Land may be taken from other factions through war.


The goals of this plugin:

 * The players should be able to take care of anti-griefing themselves.
 * Inspire politics and intrigues on your server.
 * Guilding and team spirit! :)

## Usage

Read the full userguide here: [http://massivecraft.com/dev/factions](http://massivecraft.com/dev/factions)

The chat console command is:

 * `/f`

This command has subcommands like:

* `/f create MyFactionName`
* `/f invite MyFriendsName`
* `/f claim`
* `/f map`
* ... etc

You may also read the documentation ingame as the plugin ships with an ingame help manual. Read the help pages like this:

* `/f help 1`
* `/f help 2`
* `/f help 3`

Note that you may optionally skip the slash and just write

* `f`

## Installing
1. Download the latest release: [https://github.com/MassiveCraft/Factions/downloads](https://github.com/MassiveCraft/Factions/downloads)<br>
1. Put Factions.jar in the plugins folder.
1. [Download gson.jar to your lib folder](http://massivecraft.com/plugins/libraries)

A default config file will be created on the first run.

## License
This project has a LGPL license just like the Bukkit project.<br>
This project uses [GSON](http://code.google.com/p/google-gson/) which has a [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0 ).


