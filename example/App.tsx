import { StyleSheet, Text, View } from 'react-native';

import * as ExpoCardConnect from 'expo-card-connect';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>{ExpoCardConnect.hello()}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
