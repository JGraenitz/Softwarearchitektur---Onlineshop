FROM quay.io/keycloak/keycloak:legacy

COPY realm-export.json /tmp/realm-export.json

ENV KEYCLOAK_IMPORT /tmp/realm-export.json

ENV KEYCLOAK_IDM_LOGLEVEL=DEBUG