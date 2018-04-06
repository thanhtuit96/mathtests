angular.module('mathtest.services', [])
.factory('AuthService', function($http) {
	var user = null;
	var TOKEN_KEY = 'jwtToken';
  var  EXPIRES_IN = "expires_in";
  var getJwtToken = function() {
    return localStorage.getItem(TOKEN_KEY);
  };

  var getExpiresIn = function(){
    return localStorage.getItem(EXPIRES_IN);
  };

  var setJwtToken = function(token) {
      localStorage.setItem(TOKEN_KEY, token);
  };

  var setExpiresIn = function(expires_in) {
      localStorage.setItem(EXPIRES_IN, expires_in);
  };

  var removeAuth = function() {
      localStorage.removeItem(TOKEN_KEY);
      localStorage.removeItem(EXPIRES_IN);
  };

  var createAuthorizationTokenHeader = function() {
      var token = getJwtToken();
      if (token) {
          return {
            "Authorization": "Bearer " + token,
            'Content-Type': 'application/json'
          };
      } else {
          return {
            'Content-Type': 'application/json'
          };
      }
  }
  return {
	    getJwtToken: getJwtToken,
	    getExpiresIn: getExpiresIn,
	    setJwtToken: setJwtToken,
	    setExpiresIn: setExpiresIn,
	    removeAuth: removeAuth,
	    createAuthorizationTokenHeader: createAuthorizationTokenHeader
	  };
});