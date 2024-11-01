# Start from the official Nginx image
FROM nginx:1.26.0

# Update package list and install 'unzip' for extracting files
RUN apt-get update ; apt-get install unzip

# Download the OpenTelemetry Web Server SDK tarball and add it to the /opt directory
ADD https://github.com/open-telemetry/opentelemetry-cpp-contrib/releases/download/webserver%2Fv1.1.0/opentelemetry-webserver-sdk-x64-linux.tgz /opt

# Change directory to /opt, unzip and extract the downloaded OpenTelemetry SDK package
RUN cd /opt ; unzip opentelemetry-webserver-sdk-x64-linux.tgz.zip; tar xvfz opentelemetry-webserver-sdk-x64-linux.tgz

# Change directory to the extracted OpenTelemetry SDK folder and run the install script
RUN cd /opt/opentelemetry-webserver-sdk; ./install.sh

# Set the library path environment variable to include OpenTelemetry SDK libraries
ENV LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/opt/opentelemetry-webserver-sdk/sdk_lib/lib

# Prepend the ngx_http_opentelemetry_module.so module to the Nginx configuration file
RUN echo "load_module /opt/opentelemetry-webserver-sdk/WebServerModule/Nginx/1.26.0/ngx_http_opentelemetry_module.so;\n$(cat /etc/nginx/nginx.conf)" > /etc/nginx/nginx.conf

# Copy the custom OpenTelemetry Nginx module configuration to the appropriate location
COPY opentelemetry_module.conf /etc/nginx/conf.d

# Expose port 80 to allow HTTP traffic
EXPOSE 80
