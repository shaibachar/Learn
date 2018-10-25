FROM nginx
COPY nginx.conf /etc/nginx/nginx.conf
COPY index.html /etc/nginx/html/index.html
COPY server.key /etc/nginx/server.key
COPY server.crt /etc/nginx/server.crt

# Expose ports.
EXPOSE 80
EXPOSE 443