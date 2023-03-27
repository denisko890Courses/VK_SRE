#!/bin/bash

# Crontab option: 0 0 * * * /path/to/script.sh $1 $2 $3 $4
# Запуск скрипта: ./sync_logs.sh /source/path/ remote_username remote_host /remote/path/to/sync_file
# Пользователь должен иметь доступ к машине по ключу

# Установка пути к директории, где будут созданы файлы
log_dir=${1}
remote_username=${2}
remote_host=${3}
remote_log_dir=${4}

# Создание файлов в директории
for i in {1..5}; do
  touch "${log_dir}tmp-file-${i}.log"
  echo "This is file ${i}" > "${log_dir}tmp-file-${i}.log"
done

# Копирование файлов на другую машину
rsync -avz --update "${log_dir}" ${remote_username}@${remote_host}:${remote_log_dir}

# Управление временем хранения файлов на другой машине
ssh ${remote_username}@${remote_host} find ${remote_log_dir} -type f -name "*.log" -mtime +7 -delete
