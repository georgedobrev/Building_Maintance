import { Link } from "react-router-dom";
import "./Register.scss";
import Button from "../../components/UI/Button";
const Register = () => {
  return (
    <div className="register">
      <div className="right">
        <h1>Register</h1>
        <form>
          <input type="text" placeholder="First Name" />
          <input type="text" placeholder="Last Name" />
          <input type="email" placeholder="Email" />

          <input type="password" placeholder="Password" />
          <Button>Register</Button>
        </form>
      </div>
      <div className="left">
        <h1>Get Started and manage the place where you live in.</h1>
        <span>You already have an account?</span>
        <Link to="/login">
          <Button>Login</Button>
        </Link>
      </div>
    </div>
  );
};

export default Register;
