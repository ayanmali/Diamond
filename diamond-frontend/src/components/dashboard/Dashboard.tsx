import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar"
import { AppSidebar } from "@/components/ui/app-sidebar"
import { IoNotificationsOutline, IoSettingsOutline, IoAddOutline } from "react-icons/io5";
 
//export default function Layout({ children }: { children: React.ReactNode }) {
export default function Layout() {
  return (
  <div className="w-full">
    <div className="container">
      <div className="flex justify-end gap-6">
        <IoNotificationsOutline className="size-6"/>
        <IoSettingsOutline className="size-6"/>
        <IoAddOutline className="size-6"/>
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