import API from "./axiosConfig";

export const loginUser = async (email, password) => {
  const res = await API.post("/auth/login", { email, password });
  return res.data; // ✅ return only response body
};

export const registerUser = async (name, email, password, address, phone) => {
  const res = await API.post("/auth/register", {
    name,
    email,
    password,
    address,
    phone,
  });
  return res.data;
};
