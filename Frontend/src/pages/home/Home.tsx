import React from "react";

interface HomeProps {
  manager: boolean;
  currentUser: boolean;
}

const Home: React.FC<HomeProps> = ({ manager, currentUser }) => {
  return (
    <div>
      <h1>HOME</h1>
      <h1>HOME</h1>
      <h1>HOME</h1>
      <h1>Manager: {manager}</h1>
      <h1>currentUser: {currentUser}</h1>
      <h1>HOME</h1>
      <h1>HOME</h1>
      <h1>HOME</h1>
      <h1>HOME</h1>
    </div>
  );
};

export default Home;
