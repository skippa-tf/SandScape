# SandScape
SandScape is my attempt to recreate a popular sandbox genre "falling sand games."

>A falling-sand game is a genre of video game and subgenre of sandbox games using a two-dimensional particle game engine or a cellular automaton.
>
>In falling-sand games, the user can interact with (e.g. place and remove) particles on a canvas which can interact with other particles in various ways, which can lead to complex emergent behaviour. As sandbox games, they generally have an emphasis on free-form gameplay, relaxed rules, and minimal goals.
>
>Despite the name, falling-sand games typically contain a multitude of materials besides sand, often called "elements".
>
>Source: Wikipedia contributors. (2023, November 1). Falling-sand game. In Wikipedia, The Free Encyclopedia. Retrieved 18:47, January 19, 2024, from https://en.wikipedia.org/w/index.php?title=Falling-sand_game&oldid=1183029165

### Implementation
Since a falling-sand game is essentially just a pixel painter where the pixels have physics, I will first implement the 
painting portion of the program using JavaFX.

This will be my first time using JavaFX, so it will be rough.

My idea for layout looks like this:
```
┌-----------------------------------------------------------------------------┐
|                                                                             |
| ------*----           (*) Sand    ( ) Water   ( ) Stone         [CLEAR]     |
| Stroke Size           ( ) Wood    ( ) Fire    ( ) TBD           [START]     |    
|                                                                 [PAUSE]     |
|                                                                             |
|-----------------------------------------------------------------------------|
|                                                                             |
|                                                                             |
|                                                                             |
|                                  Canvas                                     |
|                             (BufferedImage?)                                |
|                                                                             |
|                                                                             |
|                                                                             |
|                                                                             |
|                                                                             |
└-----------------------------------------------------------------------------┘
```


JavaFX is required to run this project. Add it as a dependency in your IDE.