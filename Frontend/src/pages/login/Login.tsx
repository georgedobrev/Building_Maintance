import { Link } from "react-router-dom";
import "./Login.scss";
import Button from "../../components/UI/Button";
const Login = () => {
  return (
    <>
      <div className="login">
        <div className="left">
          <h1>Welcome to Blankfactor&apos;s building maintainance app.</h1>
          <p>
            This is the place to connect to your neighbours, track and manage
            payments, promote ideas and address issues you come accross.
          </p>
          <span>You do not have an account?</span>
          <Link to="/register">
            <Button>Register</Button>
          </Link>
        </div>
        <div className="right">
          <h1>Login</h1>
          <form>
            <input required type="text" placeholder="Username" />
            <input required type="password" placeholder="Password" />
            <Button>Login</Button>
          </form>
        </div>
      </div>
    </>
  );
};

export default Login;
