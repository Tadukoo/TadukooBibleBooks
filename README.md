Tadukoo Bible Books
=====================

Description
-----------
Tadukoo Bible Books is a plugin to go with the [Tadukoo Bible Project] (https://github.com/Tadukoo/TadukooBibleProject). It creates the bookconfig files and
checks them.

Command Usage
-------------
None at the moment.

Permissions
-----------
None at the moment.

Config.yml
----------
BookConfigChapterNotifications: 5
* How many chapters are made before notifying you.
* Set to 0 to never be notified.

KJVBookCheck: check
* The settings above are how to check the bookconfig.yml file for the given translation.
* Create will overwrite books even if they already exist.
* Correct will overwrite incorrect parts of books and ignore others.
* Check will simply check if each book exists and create them if they don't.
* Ignore will do nothing.

Upcoming Commands/Features
--------------------------
Add commands to mess with stuff while the server's up.

More translations.

Make it so that a page doesn't end with a verse number.

Known Issues
------------
None currently.

Download
--------
Currently you are unable to download the plugin jar file. Once this plugin is in Beta, it will be available on BukkitDev.

Currently to download and install this plugin, you must download the classes and plugin.yml file, then import them into eclipse, along with importing the
Bukkit API (Currently this plugin is built off of [1.7.9-R0.1 Beta Build 1936] (http://dl.bukkit.org/downloads/bukkit/view/02620_1.7.9-R0.1/)) and importing
[TadukooBibleProject] (https://github.com/Tadukoo/TadukooBibleProject).

Do not import the Tadukoo_Bible_Books or Tadukoo_Bible folder into eclipse. Simply place them in your plugins folder.

Version System
--------------
May be documented in the future.
