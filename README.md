# Percolation

If you have a hodgepodge of insulating and metallic stuff, how much metal do you need to make an electric flow? 
And if you have a sponge-like terrain with water (or oil) on top, what does it take for the liquid to make its way down (or up)? 
Percolation is a fancy way scientists use to figure it all out.

## Model

So, imagine a grid, a big n-by-n grid, with some of the squares blocked and some open. We call the open ones "sites." 
Now, a site is considered "full" if it's open AND it can be connected to the top row of sites via a series of adjacent (up, down, left, or right) 
open sites. If we can find a full site on the bottom row, we say the system "percolates." Basically, that means if we pour water from the top, 
it'll find a way to trickle all the way down. Or if we're dealing with metallic materials, it means there's a path from top to bottom that conducts electricity.
 
![Login](https://github.com/Shubhamg369/Private/blob/main/Screenshot%202023-02-27%20at%2010.21.00%20AM.png?raw=true)
 
## Percolation data type
So, to create a fancy model of a percolation system, we need a new "data type" called "Percolation," with a bunch of functions we can call on it - kind of like a robot with a specific set of skills with the following API.

![Login](https://github.com/Shubhamg369/Private/blob/main/Screenshot%202023-02-27%20at%2010.21.24%20AM.png?raw=true)

## Monte Carlo simulation
So, we want to figure out when our system starts percolating. Here's what we do: First, we assume everything is blocked. 
Then, we keep choosing a random blocked site and opening it up until the system finally percolates. 
We count how many sites we had to open up in total, and divide that number by the total number of sites to get our percolation threshold. 
For instance, if we're working with a 20-by-20 grid, and we had to open up 204 sites to get it to percolate, we estimate the threshold to be 0.51 (204/400), just like that!

![Login](https://github.com/Shubhamg369/Private/blob/main/Screenshot%202023-02-27%20at%2010.22.36%20AM.png?raw=true)

To get a better estimate of when our system starts percolating, we need to do more than just one trial. 
So, we repeat the same experiment T times and take the average of all our results to get a more accurate threshold estimate. 
We keep track of the fraction of open sites for each trial (we call this xt), and we calculate the mean of all those fractions (x-bar) to get our final estimate. 
The standard deviation (s) tells us how precise our estimate is. Think of it like throwing darts at a target - the closer your darts land to each other, the more precise your aim.

