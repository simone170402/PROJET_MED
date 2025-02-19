export const environment = {
  production: true,
  apiUrl: 'https://your-production-api-url/api',
  franceConnect: {
    clientId: 'your-france-connect-client-id',
    clientSecret: 'your-france-connect-client-secret',
    scopes: 'openid profile email',
    authorizeUrl: 'https://app.franceconnect.gouv.fr/api/v1/authorize',
    tokenUrl: 'https://app.franceconnect.gouv.fr/api/v1/token',
    userInfoUrl: 'https://app.franceconnect.gouv.fr/api/v1/userinfo',
    redirectUri: 'https://your-production-url/auth/callback'
  }
};
