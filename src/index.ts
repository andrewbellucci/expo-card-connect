import ExpoCardConnectModule from "./ExpoCardConnectModule";

// Get the native constant value.

export const setEndpoint = (endpoint: string) => {
  return ExpoCardConnectModule.setEndpoint(endpoint);
};

export const generateTokenForCard = (
  cardNumber: string,
  expiryDate: string,
  cvv: string,
  promise: Promise<any>,
) => {
  return ExpoCardConnectModule.generateTokenForCard(
    cardNumber,
    expiryDate,
    cvv,
    promise,
  );
};
