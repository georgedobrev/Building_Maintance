export const config = {
  baseURL: "http://localhost:8080/",
  register_manager: "user/register/manager",
  add_building: "building/create",
  login: "auth/login",
  api_all_countries: "https://restcountries.com/v3.1/all",
  get_user_role: "user/role",
  register_user: "auth/manager/register/user",
  get_managed_buildings: (id: string | number) => `building/${id}`,
  get_building_units: (buildingId: string | number) =>
    `units/managed/buildings/${buildingId}/units`,
};
