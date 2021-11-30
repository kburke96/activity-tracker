import React, {useState} from 'react';
import './Login.scss';
import PropTypes from 'prop-types';

async function loginUser(credentials) {
  console.log("Running fetch from loginUser()");
  console.log("Credentials: " + JSON.stringify(credentials));
  const baseUrl = process.env.REACT_APP_BACKEND_IP;
  console.log("baseURL: " + baseUrl);
  const signInUrl = "http://" + baseUrl + ":8080/api/auth/signin";
  const data = await fetch(signInUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(credentials)
  })
    .then(data => data.json())
  
  console.log("Data is: " + JSON.stringify(data));
  console.log("Token is: " + data.accessToken);
  return data.accessToken;
  
 }

export default function Login({ setToken }) {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();

  const handleSubmit = async e => {
    e.preventDefault();
    console.log("Calling loginUser....");
    const token = await loginUser({
      username,
      password
    });
    setToken(token);
    // this.props.history.push("/activities");
    window.location.reload();
  }

  return(
    <div className="login-wrapper">
      <h1>Please Log In</h1>
      <form onSubmit={handleSubmit}>
        <label>
          <p>Username</p>
          <input type="text" onChange={e => setUserName(e.target.value)}/>
        </label>
        <label>
          <p>Password</p>
          <input type="password" onChange={e => setPassword(e.target.value)}/>
        </label>
        <div>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  )
}

Login.propTypes = {
  setToken: PropTypes.func.isRequired
}
