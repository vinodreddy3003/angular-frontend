# Use Alpine Linux as base image
FROM alpine

# Install necessary packages
RUN apk add --no-cache \
    nodejs \
    npm \
    git \
    && npm install -g @angular/cli \
    && npm install -g json

# Set working directory
WORKDIR /app

# Copy source files to working directory
COPY . /app

# Expose any needed ports (optional)
EXPOSE 4200

# Start your application
CMD ["npm","start"]
