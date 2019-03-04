FROM node:11.4.0-alpine
RUN apk update
RUN mkdir /app
WORKDIR /usr/src/app
COPY package*.json ./
RUN apk --no-cache add --virtual builds-deps build-base python
RUN npm config set python /usr/bin/python
RUN npm i -g npm
RUN npm install --production
RUN npm rebuild bcrypt --build-from-source
RUN apk del builds-deps

COPY . .

EXPOSE 3000 3001
CMD [ "npm", "start" ]