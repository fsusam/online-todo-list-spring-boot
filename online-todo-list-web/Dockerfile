FROM node:8.15.0-alpine
WORKDIR /home/app
COPY package.json .
COPY package-lock.json .
COPY server.js .
CMD mkdir public
COPY public/* ./public/
RUN npm install --quiet