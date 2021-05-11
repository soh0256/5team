module.exports = {
  secret:
    process.env.NODE_ENV === "production" ? process.env.SECRET : "backend!@",
  db: {
    user: "REACT",
    password: "REACT",
    connectSttring: "172.30.1.3/orcl",
  },
};
