import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Image  } from 'react-native';

function Main() {
  return (
    <View style={styles.container}>


      {/* 최상단 제목 */}
      <View style={styles.titleContainer}>
        <Image source={require('../assets/main/꼬리.png')} style={styles.titleImage} />
      </View>

      {/* 경계선 */}
      <View style={styles.borderLine} />


      {/* 위쪽 섹션 */}
      <View style={[styles.section, { flex: 2, flexDirection: 'row' }]}>
        <TouchableOpacity style={[styles.button, styles.buttonMap]}>
          <Image source={require('../assets/main/지도로찾기.png')} style={styles.buttonImage} />
        </TouchableOpacity>

        <TouchableOpacity style={[styles.button, styles.buttonBoard]}>
          <Image source={require('../assets/main/구직게시판.png')} style={styles.buttonImage} />
        </TouchableOpacity>
      </View>


      {/* 가운데 */}
      <View style={[styles.section, { flex: 1 }]}>
        <Image source={require('../assets/main/배너.png')} style={styles.banner} />
      </View>

      {/* 아래쪽 */}
      {/* 아래쪽 섹션 */}
      <View style={[styles.section, { flex: 2, flexDirection: 'row' }]}>
        <TouchableOpacity style={[styles.button, styles.qrscan, { flex: 2 }]}>
          <Image source={require('../assets/main/스캔.png')} style={styles.buttonImage} />
        </TouchableOpacity>

        <View style={{ flex: 1 }}>
          <TouchableOpacity style={[styles.button, styles.lostdog, { flex: 1 }]}>
            <Image source={require('../assets/main/분실견.png')} style={styles.buttonImage} />
          </TouchableOpacity>
          <TouchableOpacity style={[styles.button, styles.kingdog, { flex: 1 }]}>
            <Image source={require('../assets/main/산책왕.png')} style={styles.buttonImage} />
          </TouchableOpacity>
        </View>
      </View>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
  titleContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 2,
  },
  titleImage: {
    width: 150, 
    height: 75, 
    resizeMode: 'contain',
  },
  borderLine: {
    borderBottomWidth: 1,
    borderBottomColor: 'grey',
  },

// 버튼배경색들
  buttonMap: {
    backgroundColor: '#FFD1B1',
  },

  buttonBoard: {
    backgroundColor: '#EFD9AE',
  },
  qrscan: {
    backgroundColor: '#FFE0C9',
  },
  lostdog: {
    backgroundColor: '#E7C9FF',
  },
  kingdog:{
    backgroundColor: '#8BD6FC',
  },



  banner: {
    width: '100%',
    height: '100%',
    resizeMode: 'contain',
  },
  buttonImage: {
    width: '100%',
    height: '100%',
    resizeMode: 'contain', 
  },
  section: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },

  button: {
  flex: 1,
  backgroundColor: '#e0e0e0',
  padding: 10,
  borderRadius: 5,
  margin: 5,
  alignItems: 'center',
  },

  image: {
    width: 50,
    height: 50, 
    resizeMode: 'contain', 
  },
});

export default Main;
