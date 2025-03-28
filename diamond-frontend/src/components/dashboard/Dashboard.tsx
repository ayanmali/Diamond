import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar"
import { AppSidebar } from "@/components/dashboard/app-sidebar"
import { IoNotificationsOutline } from "react-icons/io5";
import { NewButtonDropdown } from "./NewButtonDropdown";
import { SettingsButtonDropdown } from "./SettingsButtonDropdown";
 
//export default function Layout({ children }: { children: React.ReactNode }) {
export default function Layout() {
  return (
  <div className="w-full">
    <div className="container">
      <div className="flex justify-end gap-5 md:pt-4">
        <IoNotificationsOutline className="size-5"/>
        <SettingsButtonDropdown/>
        <NewButtonDropdown/>
      </div>
      <SidebarProvider>
        <AppSidebar />
        <main>
          <SidebarTrigger />
          {/* {children} */}
        </main>
      </SidebarProvider>
    </div>
  </div>
  )
}