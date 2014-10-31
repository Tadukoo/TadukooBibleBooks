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

First Requirements
------------------
# Check if a translation is available (<translation>: true in Tadukoo Bible config)
# Check if that translation is complete (EnumTrans.isComplete())
# Check if a translation should be entirely checked (<translation>.all.check: true in Tadukoo Bible Books config)
* If not the entire translation or if the translation isn't complete, then check if any books should be checked (<translation>.<book>.check: true in Tadukoo 
Bible Books config)
# Check the type of check that should be performed (<translation>.all.type or <translation>.<book>.type, should be create or check)

Check Requirements
------------------
# Check that the book(s) is available (EnumBooks.isAvailable(<translation>))
* If not, skip it.
# Check for the book's file (<book>.yml)
* If it doesn't exist, skip it.
# Check if the book config for it is finished (<book>Books Done: true)
* If it is, skip it.
* If not, go to Create Book Requirements.

Create Requirements
-------------------
# Check that the book(s) is available (EnumBooks.isAvailable(<translation>))
* If not, skip it.
# Check for the book's file (<book>.yml)
* If it doesn't exist, skip it.
* If it does, go to Create Book Requirements.

Create Book Requirements
------------------------

Beginning
# Set Book1's start to 1:1 (<book>Books Book1.start.c = 1 and Book1.start.v = 1)
# Print "Starting <book>..." to the console.
# Set chapter number for notification to 1 (nc = 1)
# Set chapter number limit for notification to whatever's set in the config (ncL = config chapter-notify)
# Set chapter number limit for book (cL = EnumBooks.getNum())
# Get Chapter 1's verse limit (vL = EnumChps.getNum(1))
# Put "Chapter 1/n" into temporary page (tempPage = "Chapter 1/n")
# Set page to blank (page = null)
# Set next page to blank (nextPage = null)
# Get chapter 1 verse 1 (<book>.yml ch1v1)
# Set chapter number to 1 (c = 1)
# Set verse number to 1 (v = 1)
# Put it into temporary page (tempPage = tempPage + verse)
# Set page number to 1 (pageNum = 1)
# Set book number to 1 (bookNum = 1)
# Set book done to false (bookDone = false)

Loop
# Check if page is blank. (page != null)
* If not, put it into the file. (Book#.# = page)
* Then set it to blank. (page = null)
* Check if the page number is 50 (pageNum == 50)
* If it is, set the end values, set the page number to 0, and increment the book number. (Book#.End.c = c, Book#.End.v = v, pageNum = 0, and bookNum++)
* If the book number was incremented and nextPage isn't blank, add "Chapter # Cont./n" to nextPage (nextPage = "Chapter # Cont./n" + nextPage)
* If the book number was incremented, set book done to true (bookDone = true)
* Then increment the page number (pageNum++)
* If it is blank, continue.
# Check if the next page is blank. (nextPage != null)
* If not, set the temporary page to it and set it to blank. (tempPage = nextPage and nextPage = null)
# Check if verse limit is reached (v == vL)
* If so, check if chapter limit is met. (c == cL)
* If so, set bible done to true (bibleDone = true)
* If so, increment chapter number and set verse number to 0 (c++ and v = 0)
* If so, get new verse limit. (EnumChps.getNum(c))
* Put temporary page into page. (page = tempPage)
* If chapter-notify is met, print "Starting <book> Chapter #..." to console and reset nc (nc == ncL and nc = 0)
* Increment chapter number for notification (nc++)
* Set next page to "Chapter #/n" (nextPage = "Chapter #/n")
* Set temporary page to blank. (tempPage = null)
* Start the loop over.
* If not, increment verse number (v++)
# Check if a book was just completed (bookDone)
* If so, set up the start stuff for the new one and set book done to false (Book#.start.c = c, Book#.start.v = v, and bookDone = false)
# Get the next verse (<book>.yml ch#v#)
# Put it into temporary page (tempPage = tempPage + " " + v + " " + verse)
# Check if temporary page length is less than 256 (tempPage.length < 256)
* If so, start loop over.
* If not, continue.
# Break tempPage into words. (String[] words = tempPage.split(" "))
# Add one word at a time after checking if adding a new word would push the length past 256.
# When adding a new word would push it past 256, don't add it.
# Finish the page (page = tempPage)
# Add the rest of the words to the next page (nextPage)
# Set temporary page to empty (tempPage = null)

Current Timing
--------------
Currently, it appears that each chapter takes between 1 and 5 seconds to generate, with an average of 3 seconds.

Upcoming Commands/Features
--------------------------
Add commands to mess with stuff while the server's up.

Make config changes so that it's shorter.

Known Issues
------------
None currently.

Download
--------
Currently you are unable to download the plugin jar file. Once this plugin is finished (which won't be until after [Tadukoo Bible] 
(https://github.com/Tadukoo/TadukooBibleProject) is finished), it will be available on BukkitDev.

Currently to download and install this plugin, you must download the classes and plugin.yml file, then import them into eclipse, along with importing the
Bukkit API (Currently this plugin is built off of [1.7.9-R0.2 Beta Build 1938] (http://dl.bukkit.org/downloads/bukkit/view/02630_1.7.9-R0.2/)) and importing
[TadukooBibleProject] (https://github.com/Tadukoo/TadukooBibleProject) (Currently this plugin uses Tadukoo Bible version Beta 0.5).

Do not import the Tadukoo_Bible_Books or Tadukoo_Bible folders into eclipse. Simply place them in your plugins folder.

Version System
--------------
May be documented in the future.
