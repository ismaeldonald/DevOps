def buildApp() {
  echo 'Building Angular application...'
  echo "Building version ${NEW_VERSION}"
}

def testApp() {
  echo 'Testing Angular application...'
}

def deployApp() {
  echo 'Deploying Angular application...'
  echo "Deploying with $SERVER_CREDENTIALS"
}

return this
