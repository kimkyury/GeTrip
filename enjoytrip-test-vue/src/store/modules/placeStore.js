import http from "@/common/axios.js";

const placeStore = {
  namespaced: true,
  state: {
    limit: 10,
    offset: 0,
    sidoCode: "0",
    gugunCode: "0",
    searchWord: "",
    areaList1: [],
    areaList2: [],
    trips: [],
    positions: [],
    markers: [],

    // detail
    contentId: "",
    overview: "",
    title: "",
    addr1: "",
    firstImage: "",
    latitude: "",
    longitude: "",
  },
  getters: {
    getAreaList1: function (state) {
      return state.areaList1;
    },
    getAreaList2: function (state) {
      return state.areaList2;
    },
    getTrips: function (state) {
      return state.trips;
    },
  },

  actions: {
    async mapView({ state, dispatch }) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=20d8b1fb60682e2256b9d4e10ea6a783&libraries=services,clusterer,drawing`;

      /*global kakao*/
      script.addEventListener("load", () => {
        // 스크립트 로딩이 완료되었을 때 실행되는 코드
        // Kakao 지도 API를 사용하는 코드를 이곳에 작성합니다.
        kakao.maps.load(async () => {
          // 카카오맵 API 로드 완료 후 실행할 코드
          // 지도 생성, 마커 추가 등을 이곳에 작성하면 됩니다.
          console.log("loaded", kakao);

          let mapContainer = document.getElementById("map"), // 지도를 표시할 div
            mapOption = {
              center: new window.kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
              level: 5, // 지도의 확대 레벨
            };

          // // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
          state.map = new window.kakao.maps.Map(mapContainer, mapOption);

          await dispatch("getArea1List");
          await dispatch("getList");
        });
      });
      document.head.appendChild(script);
    },
    async getArea1List({ commit }) {
      let { data } = await http.get("/codes");
      commit("SET_AREA_LIST_1", data.list);
    },
    async getArea2List({ state, commit }) {
      commit("SET_GUGUN_CODE", "0");
      let { data } = await http.get("/codes/" + state.sidoCode);
      commit("SET_AREA_LIST_2", data.list);
    },
    async getList({ state, dispatch }) {
      let params = {
        limit: state.limit,
        offset: state.offset,
        sidoCode: state.sidoCode,
        gugunCode: state.gugunCode,
        searchWord: state.searchWord,
      };

      let { data } = await http.get("/trips", { params });

      await dispatch("makeCard", data);
    },
    async makeCard({ state, dispatch, commit }, data) {
      commit("SET_POSITIONS", []);
      commit("SET_TRIPS", data.list);

      if (state.trips.length == 0) {
        alert("검색결과가 없습니다.");
        return;
      }

      state.trips.forEach((area) => {
        let markerInfo = {
          title: area.title,
          latlng: new window.kakao.maps.LatLng(area.latitude, area.longitude),
        };
        commit("PUSH_POSITIONS", markerInfo);
      });
      await dispatch("displaymarker");
    },
    async displaymarker({ state, commit }) {
      for (let i = 0; i < state.markers.length; i++) {
        state.markers[i].setMap(null);
      }
      commit("SET_MARKERS", []);
      console.log(state.markers);

      // 마커 이미지의 이미지 주소입니다
      let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      for (let i = 0; i < state.positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        let imageSize = new window.kakao.maps.Size(24, 35);
        // 마커 이미지를 생성합니다
        let markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        let marker = new window.kakao.maps.Marker({
          map: state.map, // 마커를 표시할 지도
          position: state.positions[i].latlng, // 마커를 표시할 위치
          title: state.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        commit("PUSH_MARKERS", marker);
      }
      // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
      state.map.setCenter(state.positions[0].latlng);
    },
    async getTripDetail({ commit }, contentId) {
      let { data } = await http.get("/trips/" + contentId);
      commit("SET_CONTENT", data);
    },
    async moveCenter({ state }, { lat, lng }) {
      state.map.setCenter(new window.kakao.maps.LatLng(lat, lng));
    },
  },
  mutations: {
    SET_GUGUN_CODE(state, gugunCode) {
      state.gugunCode = gugunCode;
    },
    SET_AREA_LIST_1(state, data) {
      state.areaList1 = data;
    },
    SET_AREA_LIST_2(state, data) {
      state.areaList2 = data;
    },
    SET_POSITIONS(state, data) {
      state.positions = data;
    },
    PUSH_POSITIONS(state, markerInfo) {
      state.positions.push(markerInfo);
    },
    SET_TRIPS(state, dataList) {
      state.trips = dataList;
    },
    SET_MARKERS(state, markers) {
      state.markers = markers;
    },
    PUSH_MARKERS(state, marker) {
      state.markers.push(marker);
    },
    SET_CONTENT(state, data) {
      state.contentId = data.dto.contentId;
      state.overview = data.dto.overview;
      state.title = data.dto.title;
      state.addr1 = data.dto.addr1;
      state.firstImage = data.dto.firstImage;
      state.latitude = data.dto.latitude;
      state.longitude = data.dto.longitude;
    },
  },
};

export default placeStore;
