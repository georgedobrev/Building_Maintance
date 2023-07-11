export const config = {
  baseURL: "http://localhost:8080/",
  register_manager: "user/register/manager",
  add_building: "building/create",
  login: "auth/login",
  api_all_countries: "https://restcountries.com/v3.1/all",
  get_user_role: "user/role",
  register_user: "auth/manager/register/user",
  create_announcement: "notification/send",
  get_notificationsById: (buildingId: string | undefined) =>
    `notification/building/${buildingId}`,
  get_managed_buildings: `user/managed/buildings`,
  get_building_units: (buildingId: string) =>
    `units/managed/buildings/${buildingId}/units`,
};
