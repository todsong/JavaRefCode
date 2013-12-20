#include<stdio.h>
#include<unistd.h>
#include<sys/select.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<sys/un.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<errno.h>
#include<signal.h>
int main()
{
    signal(SIGPIPE,SIG_IGN);
    struct sockaddr_in servaddr;
    memset(&servaddr,0,sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(9999);
    servaddr.sin_addr.s_addr = inet_addr("192.168.1.101");
    int sockfd;
    int len = sizeof(servaddr);
//        sockfd = socket(AF_INET, SOCK_STREAM, 0);
    while(1)
    {
        sockfd = socket(AF_INET, SOCK_STREAM, 0);
        if(-1 == connect(sockfd, (struct sockaddr*)&servaddr, len))
        {
            printf("connect fail, sockfd=%d, errno=%d\n",sockfd,errno);
            sleep(1);
            continue;
        }
        char sendBuf[] = "hello";
        char revBuf[128] = {0};
        while(1)
        {
            int res = write(sockfd, sendBuf, strlen(sendBuf)+1);
            if(res<=0)
            {
                printf("write fail, errno=%d, res=%d\n",errno,res);
                break;
            }
            memset(revBuf, 0 , sizeof(revBuf));
            res = read(sockfd, revBuf, 128);
            if(res==-1)
            {
                printf("read fail, errno=%d\n",errno);
                break;
            }
            else if(res==0)
            {
                break;
            }
            
            printf("echo from server= %s\n",revBuf);
            sleep(1);
        }
        close(sockfd);
    }
    close(sockfd);
    printf("client close\n");
    return 0;
}
