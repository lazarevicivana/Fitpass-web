<script>
import SideBarLink from './SideBarLink'
import { collapsed, toggleSidebar, sidebarWidth } from './state'
export default {
  props: {
    user : Object,
    loggedIn : Object
  },
  components: { SideBarLink },
  setup() {
    return { collapsed, toggleSidebar, sidebarWidth }
  }
}
</script>

<template>
  <div class="sidebar" :style="{ width: 180 }">
    <h1>
      <span v-if="collapsed">
        <div>V</div>
        <div>S</div>
      </span>
      <span v-else>Olympus Corp</span>
    </h1>

    <SideBarLink to="/" icon="fas fa-home" >Home</SideBarLink>
    <SideBarLink to="/facilities" icon="fas fa-home" >Facilities</SideBarLink>

    <SideBarLink v-if="loggedIn" to="/profile" icon="fas fa-home">User profile</SideBarLink>

    <SideBarLink v-if="user.userRole === 'ADMIN'" to="/add-facility" icon="fas fa-home" >Create facility</SideBarLink>

    <SideBarLink v-if="user.userRole === 'ADMIN'" to="/create-manager" icon="fas fa-home" >Add manager</SideBarLink>
    <SideBarLink v-if="user.userRole === 'ADMIN'" to="/create-trainer" icon="fas fa-home" >Add trainer</SideBarLink>
    <SideBarLink v-if="user.userRole === 'ADMIN'" to="/promo-codes" icon="fas fa-home" >Promo code</SideBarLink>
    <SideBarLink v-if="user.userRole === 'ADMIN'" to="/all-users" icon="fas fa-home" >Users</SideBarLink>
    <SideBarLink v-if="user.userRole === 'MANAGER'" to="/manager-facility" icon="fas fa-home" >Facility</SideBarLink>
    <SideBarLink v-if="user.userRole === 'MANAGER'" to="/manager-trainings" icon="fas fa-home" >Trainings</SideBarLink>
    <SideBarLink v-if="user.userRole === 'MANAGER'" to="/manager-trainers" icon="fas fa-home" >Trainers</SideBarLink>
    <SideBarLink v-if="user.userRole === 'CUSTOMER'" to="/memberships" icon="fas fa-home" >Memberships</SideBarLink>
    <SideBarLink to="/about" icon="fas fa-home" >About</SideBarLink>

<!--    <SidebarLink to="/friends" icon="fas fa-users">Friends</SidebarLink>-->
<!--    <SidebarLink to="/image" icon="fas fa-image">Images</SidebarLink>-->

    <span
        class="collapse-icon"
        :class="{ 'rotate-180': collapsed }"
        @click="toggleSidebar"
    >
      <i class="fas fa-angle-double-left" />
    </span>
  </div>
</template>

<style>
:root {
  --sidebar-bg-color: #072238;
  --sidebar-item-hover: #263E51;
  --sidebar-item-active: #263E51;
}
</style>

<style scoped>
.sidebar {
  color: white;
  background-color: var(--sidebar-bg-color);
  float: left;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  bottom: 0;
  padding: 0.5em;
  transition: 0.3s ease;
  display: flex;
  flex-direction: column;
}
.sidebar h1 {
  height: 2.5em;
}
.collapse-icon {
  position: absolute;
  bottom: 0;
  padding: 0.75em;
  color: rgba(255, 255, 255, 0.7);
  transition: 0.2s linear;
}
.rotate-180 {
  transform: rotate(180deg);
  transition: 0.2s linear;
}
</style>