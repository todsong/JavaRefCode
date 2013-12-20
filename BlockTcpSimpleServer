#include<stdio.h>
#include<unistd.h>
#include<sys/select.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<sys/un.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<errno.h>
#include <signal.h>
int main()
{
    signal(SIGPIPE, SIG_IGN);
    int serv_sockfd = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in servaddr, cliaddr;
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(9999);
    servaddr.sin_addr.s_addr = inet_addr("0.0.0.0");
    int len = sizeof(servaddr);
    bind(serv_sockfd, (struct sockaddr*)&servaddr, len);
    listen(serv_sockfd, 2);

    char recvBuf[128] = {0};
    int cli_sockfd;
    while(1)
    {
        cli_sockfd = accept(serv_sockfd, (struct sockaddr*)&cliaddr, &len);
        if(cli_sockfd==-1)
        {
            printf("accept fail, errno=%d", errno);
            continue;
        }
        while(1)
        {
            memset(recvBuf, 0, sizeof(recvBuf));
            int res = read(cli_sockfd, recvBuf, 128);
            if(res==-1)
            {
                printf("read fail, errno=%d\n",errno);
                break;
            }
            else if(res==0)
            {
                printf("read null\n");
                break;
            }
            printf("read from client: %s\n",recvBuf);
            recvBuf[0] = 'x';
            res = send(cli_sockfd, recvBuf, strlen(recvBuf)+1,0);
            if(res<=0)
            {
                printf("send fail, res=%d, errno=%d\n",res, errno);
                close(cli_sockfd);
                break;
            }
        }
    }
    printf("close\n");
    close(cli_sockfd);
    return 0;
}
