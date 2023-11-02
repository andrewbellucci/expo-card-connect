import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoCardConnect.web.ts
// and on native platforms to ExpoCardConnect.ts
import ExpoCardConnectModule from './ExpoCardConnectModule';

// Get the native constant value.
export const PI = ExpoCardConnectModule.PI;

export function hello(): string {
  return ExpoCardConnectModule.hello();
}

export const setEndpoint = (endpoint: string) => ExpoCardConnectModule.setEndpoint(endpoint);

export const generateTokenForCard = (
  cardNumber: string,
  expiryDate: string,
  cvv: string,
) => ExpoCardConnectModule.generateTokenForCard(cardNumber, expiryDate, cvv);

export async function setValueAsync(value: string) {
  return await ExpoCardConnectModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoCardConnectModule ?? NativeModulesProxy.ExpoCardConnect);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoCardConnectView, ExpoCardConnectViewProps, ChangeEventPayload };
