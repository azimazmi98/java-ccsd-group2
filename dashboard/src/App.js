import { ColorModeContext, useMode } from "./base/theme";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Routes , Route } from "react-router-dom";
import Topbar from "./HodViews/scense/global/Topbar";
import SidebarAdmin from "./HodViews/scense/global/SidebarAdmin";
import DashboardAdmin from "./HodViews/scense/dashboard";
import TeamAdmin from "./HodViews/scense/team";
import AddTeam from "./HodViews/scense/team/addTeam";
import SignInSide from "./FrontEnd/scense/SignInSide";
import WebsiteComponentsAdmin from "./HodViews/scense/websites/websiteComponents";
import Services from "./HodViews/scense/services";
import AddProduct from "./HodViews/scense/services/addProduct";
import AddWebsiteText from "./HodViews/scense/websites/addWebsiteTexts";
import AddWebsiteImage from "./HodViews/scense/websites/addWebsiteImage";
import AddWebsiteGallery from "./HodViews/scense/websites/addWebsiteGallery";
import Landing from "./landingpage/landing";
import "./App.css";
import Payment from "./payment/payment";
import ProtectedRoute from "./Auth/ProtectedRoute";
import { useState } from "react";
import RegisterCustomer from "./register-customer/register-customer";

const HodViewsLayout = ({ children }) => {
  const [theme, colorMode] = useMode();

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <div className="app">
          <SidebarAdmin />
          <main className="content">
            <Topbar />
            {children}
          </main>
        </div>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
};

// Update the App component to use HodViewsLayout for HodViews pages
function App() {

  const [isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem("isLoggedIn") === "true");

  return (
    <Routes>
      
      <Route
        path="/"
        element={
          <Landing />
        }
      />
      <Route
        path="/sign-in"
        element={
          <SignInSide setIsAuthenticated={setIsAuthenticated} />
        }
      />
      <Route
        path="/register"
        element={
          <RegisterCustomer />
        }
      />
      <Route
        path="/payment"
        element={
          <Payment />
        }
      />
      <Route
        path="/payment/:productSlug"
        element={
          <Payment />
        }
      />

      <Route
        path="/dashboard-admin"
        element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <HodViewsLayout>
              <DashboardAdmin />
            </HodViewsLayout>
          </ProtectedRoute>
          
        }
      />
      <Route
        path="/team-admin"
        element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <HodViewsLayout>
              <TeamAdmin />
            </HodViewsLayout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/addteam"
        element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <HodViewsLayout>
              <AddTeam />
            </HodViewsLayout>
          </ProtectedRoute>
        }
      />

      <Route
        path="/website-components-admin"
        element={
          <HodViewsLayout>
            <WebsiteComponentsAdmin />
          </HodViewsLayout>
        }
      />
      <Route
        path="/website-text-admin"
        element={
          <HodViewsLayout>
            <AddWebsiteText />
          </HodViewsLayout>
        }
      />
      <Route
        path="/website-image-admin"
        element={
          <HodViewsLayout>
            <AddWebsiteImage />
          </HodViewsLayout>
        }
      />
      <Route
        path="/website-image-gallery"
        element={
          <HodViewsLayout>
            <AddWebsiteGallery />
          </HodViewsLayout>
        }
      />

      <Route
        path="/services"
        element={
          <HodViewsLayout>
            <Services />
          </HodViewsLayout>
        }
      />
      <Route
        path="/add-product"
        element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <HodViewsLayout>
              <AddProduct />
            </HodViewsLayout>
          </ProtectedRoute>
        }
      />

      
    </Routes>
  );
}

export default App;
