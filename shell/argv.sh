#/bin/bash
if [ $# -ne 1 ];then
    echo 'para error: <topoName>'
    exit 1
fi
echo $1
