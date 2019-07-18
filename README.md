# QuickquicksortSort

This repository contains a paper and some code from a project I 
did for an Algorithms and Analysis course in college answering the
following question:

QuickSort has a good best-case runtime of Theta(n\*lg(n)). However,
if the pivot value chosen at each step is "bad" due to starting
with an already-sorted or nearly-sorted list, the runtime can be 
as bad as O(n^2). The problem of bad choice of partition can be
mitigated by, instead of choosing a particular array index as the 
pivot each time, the pivot is chosen strategically by taking the median
of three array values or chosen randomly. 

Consider choosing the median of three array values as the pivot.
What would be the impact on performance if we were to choose from
a set of five or seven possible values instead, increasing our chances
of getting a pivot value close to the actual median of the portion
of the list to be sorted? What would be the impact on performance
if we used another sorting method, such as insertion sort or 
*another iteration of QuickSort*, to determine the median of those values?

I thought it would be particularly hilarious to use QuickSort to find
the median of five or seven possible pivot values to optimize
an "outer" iteration of QuickSort. I call this sorting method QuickquicksortSort.
I implemented this and took a look at its performance in the attached paper. 

**Spoiler alert:** The performance is not great. Unfortunately
QuickquicksortSort turned out not to be the next revolution in sorting
algorithms. But I still think the concept
is very funny. Nobody I've explained it to so far  has agreed
except my Algorithms and Analysis professor, so I present this work on
GitHub in the hopes that someone else will get a kick out of it.

The included paper is an abbreviated version of my original paper for the course,
which also included explanations of the theoretical and experimental runtimes
of selection, insertion, and merge sorts. I assume the reader is familiar with 
these sorts and have included only the review of the complexity of Quicksort as 
a refresher for the reader.