### 11/2 3:46pm
initial commit. Let's try to get this done as soon as possble! :)

### 11/13 7:59pm
initial set up done. We got this! :)

### 11/14 2:45am
finished 60% of Search Point class. Still need to work on g() method and compareTo() method.

After finish the search point class can move on the next half section.

### 11/14 3:29am
Finished CompareTo method

### 11/14 4:55am
Finished raw implementation. Time to debug and re-implement

### 11/14 6:54am
Raw implementation

### 11/14 10:57pm
First time partially worked

### 11/14 11:53PM
NullPointException ressolved. Realize that the bug is with g() function. Currently the g() is always giving the value 0. And that is because the bug in line 338. Where we try to add things in to the SPpath list. The newly created SearchPoint always have the prev field as null so nothing could be added into the list.

### 11/15 4:14am
g() has value, refined path building
Points still tend to get confused at crossings
I think it is because of re-putting explored points to frontier

### 11/15 5:00am
Fixed the confusing at crossing problem by not re-putting
explored points to frontier.
Also found that heuristic option1 (the Manhattan heuristic)
was wrong because simply substracint one from another won't
give you absolute value. And distance should be an absolute
value. 
Got that fixed as well.

### 11/15 5:44am
Finished Code clean up

### 11/15 6:00am
Conclude project
