#include<stdio.h>
#include<pthread.h>
#include<unistd.h>
#include<stdlib.h>
#define T_NUM 4

pthread_mutex_t m[T_NUM];

void* print(void* arg)
{
	int i,j;
	int id = *(char*)arg-'a';
	int next = (id+1)%T_NUM;
	for(i=1;i<=5;i++)
	{
		pthread_mutex_lock(&m[id]);
		printf("t_%c:%d\n",*(char*)arg,i);
		pthread_mutex_unlock(&m[next]);
	}
	return;
}

int main()
{
	pthread_t tid[T_NUM];
	char ch[T_NUM];
	int i;
	void *res;
	for(i=1;i<T_NUM;i++)
		pthread_mutex_lock(&m[i]);
	for(i=0;i<T_NUM;i++)
	{
		ch[i] = 'a'+i;
		pthread_create(&tid[i],NULL,print,(void*)&ch[i]);
	}

	for(i=0;i<T_NUM;i++)
		pthread_join(tid[i],&res);
	return 0;
}
