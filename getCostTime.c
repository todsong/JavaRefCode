#include<stdio.h>
#include<time.h>
#include<sys/time.h>
#define USECS 1000000
int main()
{
	struct timeval t_start, t_end;
	gettimeofday(&t_start, NULL);

	//do something

	gettimeofday(&t_end, NULL);
	int sec = t_end.tv_sec - t_start.tv_sec;
	int usec = t_end.tv_usec - t_start.tv_usec;
	if(usec < 0)
	{
		usec += USECS;
	}
	printf("Cost time: %d.%06d sec\n", sec, usec);

	return 0; 
}
