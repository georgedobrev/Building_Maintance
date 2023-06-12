import HomeProps from "./HomeProps";

const Home: React.FC<HomeProps> = ({ dummyProp1 }) => {
  return (
    <div>
      <h1>TEST</h1>
      <h1>Prop 1: {dummyProp1}</h1>
      <h1>TEST</h1>
      <h1>TEST</h1>
      <h1>TEST</h1>
      <h1>TEST</h1>
    </div>
  );
};

export default Home;
