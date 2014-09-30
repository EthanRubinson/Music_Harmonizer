Music_Harmonizer
================

Harmonizes and Plays Music in C Major on a Virtual Keyboard

Instructions:

Step 1)
-
Open a web browser and navigate to http://www.onlinepianist.com/virtual_piano/

* You will need to switch to this window within 3 seconds of starting the Java application as it simulates keyboard presses
        
Step 2)
-
If desired, encode a new song (in C Major) into the application adhering to the following format:

NOTE1|LENGTH  NOTE2|LENGTH  NOTE3|LENGTH

NOTEX = {C, D, E, F, G, A, or B}

LENGTH = {.5-> 1/8 note, 1->1/4 note, 2-> 1/2 note, etc}

Step 3)
-
Run the application with 'songSelection' set equal to your desired song encoding.

Step 4)
-
Switch the the virtual piano window and ensure it has focus for keyboard input

====

* The application will first play the encoding with no chords and no harmonization.
* Afterwards, it will transform all the single notes into valid chords and play it again.
* Finally, it will harmonize the chords and play the song for a third time.
