# Script to generate the requirements.txt file for the Python service.
#!/bin/bash

cd ./diamond_py
source ./bin/activate

pip freeze > requirements.txt
deactivate
