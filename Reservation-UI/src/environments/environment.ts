export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api',
  franceConnect: {
    clientId: 'your-france-connect-client-id', // Replace with your actual client ID
    clientSecret: 'your-france-connect-client-secret', // Replace with your actual client secret
    scopes: 'openid profile email',
    authorizeUrl: 'https://fcp.integ01.dev-franceconnect.fr/api/v1/authorize',
    tokenUrl: 'https://fcp.integ01.dev-franceconnect.fr/api/v1/token',
    userInfoUrl: 'https://fcp.integ01.dev-franceconnect.fr/api/v1/userinfo',
    redirectUri: 'http://localhost:4200/auth/callback'
  }
};
