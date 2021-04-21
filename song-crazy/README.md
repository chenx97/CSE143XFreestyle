# Song (with vectors)

## Overview

Your first program will require the use of functions and
printlnstatements.  This assignment is worth 10 points instead of the normal
20 points.  You are going to write a Java program that produces as output
a cumulative song in which successive verses build on previous verses
(as described [here](http://en.wikipedia.org/wiki/Cumulative_song)). Your
program should produce as output the following song:

```
There was an old woman who swallowed a fly.
I don't know why she swallowed that fly,
Perhaps she'll die.

There was an old woman who swallowed a spider,
That wriggled and iggled and jiggled insideher.
She swallowed the spider to catch the fly,
I don't know why she swallowed that fly,
Perhaps she'll die.

There was an old woman who swallowed a bird,
How absurd to swallow a bird.
She swallowed the bird to catch the spider,
She swallowed the spider to catch the fly,
I don't know why she swallowed that fly,
Perhaps she'll die.

There was an old woman who swallowed a cat,
Imagine that to swallow a cat.
She swallowed the cat to catch the bird,
She swallowed the bird to catch the spider,
She swallowed the spider to catch the fly,
I don't know why she swallowed that fly,
Perhaps she'll die.

There was an old woman who swallowed a dog,
What a hog to swallow a dog.
She swallowed the dog to catch the cat,
She swallowed the cat to catch the bird,
She swallowed thebird to catch the spider,
She swallowed the spider to catch the fly,
I don't know why she swallowed that fly,
Perhaps she'll die.

<< Your custom sixth verse goes here >>

There was an old woman who swallowed a horse,
She died of course.
```

As indicated above, you should include a custom sixth verse that matches the
pattern of the first five verses. You must exactly reproduce the format of
this output.

Most of our assignments will have a creative aspect where you have more
flexibility than normal to come up with your own solution. For this assignment,
it involves writing a sixth verse that fits the pattern of the first five. For
example, some versions of the song have a sixth verse for swallowing a goat
(“Just opened her throat to swallow a goat”).Notice that the first two lines
should either end in the same word (fly/fly, bird/bird, cat/cat, etc) or should
end with rhyming words (spider/inside her). You are not allowed to simply copy
one of the previous animals or to use the verses you’ll find on the web(e.g.,
goat and cow).You have to write your own verse. The text of the verse should
not include hateful, offensive, or otherwise inappropriate speech.

## Expectations
You are to make use of functions to avoid the “simple” redundancy. In particular, you are to make sure that you use only one println statement for each distinct line of the song. For example, this line:

`Perhaps she'll die.`

appears several times in the output. You are to have only one println statement in your program for producing this line. The more complex redundancy has to do with pairs of lines like these:
There was an old woman who swallowed a horse,
There was an old woman who swallowed a dog,
and like these:
```
She swallowed the dog to eat the cat,
She swallowed the cat to eat the bird,
```
It is not possible to avoid this redundancy using just functions and simple println statements, so you are not expected to do so. There is, however, a structural redundancy that you can eliminate with functions and this will be worth a point. The key question to ask yourself is whether or not you have repeated lines of code that could be eliminated if you structured your functions differently. You should also use functions to capture the structure of the song. You must have a different function for each of the seven verses (verses are separated by blank lines in the output). As a result, you will not have any println statements in main except perhaps a println that produces a blank line. Main will have exactly seven function calls to produce the seven verses, although you will have more than seven functions in the class.