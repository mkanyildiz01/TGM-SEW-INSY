#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int main( int argc, char** argv)
{
  int *pint;
  int i;

  for(i=0; i<10; i++)
  {
    if( !(pint = (int*) malloc(INT_MAX*sizeof(int))))
    {
      printf("\nSorry, habe keinen freien Speicherplatz mehr!");
      printf("\nProgramm wird beendet ...\n");
      return EXIT_FAILURE;
    }

    *pint = i+1;
    printf("\nHier ist die %d. Iteration\n",*pint);
  }
  return EXIT_SUCCESS;
}
